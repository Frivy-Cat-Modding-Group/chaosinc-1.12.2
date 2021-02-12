//this was derived from the vanilla code
package com.chaosDog.Chaosinc.tileentity.furnace;


import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;

public class TEMossyFurnace extends TileEntityLockable implements ITickable, ISidedInventory
{
    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
    private static final int[] SLOTS_SIDES = new int[] {1};
    /** The ItemStacks that hold the items currently being used in the furnace */
    private NonNullList<ItemStack> furnaceItemStacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    /** The number of ticks that the furnace will keep burning */
    private int furnaceBurnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String furnaceCustomName;
    /**
     * Returns the number of slots in the inventory.
     */
    @Override
	public int getSizeInventory()
    {
        return furnaceItemStacks.size();
    }
    @Override
	public boolean isEmpty()
    {
        for (ItemStack itemstack : furnaceItemStacks)
        {
            if (!itemstack.isEmpty())
                return false;
        }
        return true;
    }
    /**
     * Returns the stack in the given slot.
     */
    @Override
	public ItemStack getStackInSlot(int index)
    {
        return furnaceItemStacks.get(index);
    }
    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    @Override
	public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(furnaceItemStacks, index, count);
    }
    /**
     * Removes a stack from the given slot and returns it.
     */
    @Override
	public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(furnaceItemStacks, index);
    }
    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
	public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = furnaceItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        furnaceItemStacks.set(index, stack);
        if (stack.getCount() > 64)
            stack.setCount(64);
        if (index == 0 && !flag)
        {
            totalCookTime = getCookTime(stack);
            cookTime = 0;
            markDirty();
        }
    }
    /**
     * Get the name of this object. For players this returns their username
     */
    @Override
	public String getName()
    {
        return hasCustomName() ? furnaceCustomName : "container.furnace";
    }
    /**
     * Returns true if this thing is named
     */
    @Override
	public boolean hasCustomName()
    {
        return furnaceCustomName != null && !furnaceCustomName.isEmpty();
    }
    public void setCustomInventoryName(String name)
    {
        furnaceCustomName = name;
    }
    public static void registerFixesFurnace(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityFurnace.class, new String[] {"Items"}));
    }
    @Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        furnaceItemStacks = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, furnaceItemStacks);
        furnaceBurnTime = compound.getInteger("BurnTime");
        cookTime = compound.getInteger("CookTime");
        totalCookTime = compound.getInteger("CookTimeTotal");
        currentItemBurnTime = getItemBurnTime(furnaceItemStacks.get(1));

        if (compound.hasKey("CustomName", 8))
            furnaceCustomName = compound.getString("CustomName");
    }
    @Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)furnaceBurnTime);
        compound.setInteger("CookTime", (short)cookTime);
        compound.setInteger("CookTimeTotal", (short)totalCookTime);
        ItemStackHelper.saveAllItems(compound, furnaceItemStacks);
        if (hasCustomName())
            compound.setString("CustomName", furnaceCustomName);
        return compound;
    }
    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */

    @Override
	public int getInventoryStackLimit()
    {
        return 64;
    }
    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return furnaceBurnTime > 0;
    }
    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }
    /**
     * Like the old updateEntity(), except more generic.
     */
    @SuppressWarnings("static-access")
	@Override
	public void update()
    {
        boolean flag = isBurning();
        boolean flag1 = false;
        if (isBurning())
            furnaceBurnTime--;
        if (!world.isRemote)
        {
            ItemStack itemstack = furnaceItemStacks.get(1);
            if (isBurning() || !itemstack.isEmpty() && !furnaceItemStacks.get(0).isEmpty())
            {
                if (!isBurning() && canSmelt())
                {
                    furnaceBurnTime = getItemBurnTime(itemstack);
                    currentItemBurnTime = furnaceBurnTime;
                    if (isBurning())
                    {
                        flag1 = true;
                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);
                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                furnaceItemStacks.set(1, item1);
                            }
                        }
                    }
                }
                if (isBurning() && canSmelt())
                {
                    ++cookTime;
                    if (cookTime == totalCookTime)
                    {
                        cookTime = 0;
                        totalCookTime = getCookTime(furnaceItemStacks.get(0));
                        smeltItem();
                        flag1 = true;
                    }
                }
                else
                    cookTime = 0;
            }
            else if (!isBurning() && cookTime > 0)
                cookTime = MathHelper.clamp(cookTime - 2, 0, totalCookTime);
            if (flag != isBurning())
            {
                flag1 = true;
                Misc.MossyFurnace.setState(isBurning(), world, pos);
            }
        }
        if (flag1)
            markDirty();
    }
    public int getCookTime(ItemStack stack)
    {
        return 200;
    }
    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (furnaceItemStacks.get(0).isEmpty())
            return false;
        else
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(furnaceItemStacks.get(0));
            if (itemstack.isEmpty())
                return false;
            else
            {
                ItemStack itemstack1 = furnaceItemStacks.get(2);
                if (itemstack1.isEmpty()) return true;
                if (!itemstack1.isItemEqual(itemstack)) return false;
                int result = itemstack1.getCount() + itemstack.getCount();
                return result <= getInventoryStackLimit() && result <= itemstack1.getMaxStackSize();
            }
        }
    }
    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (canSmelt())
        {
            ItemStack itemstack = furnaceItemStacks.get(0);
            ItemStack itemstack1 = FurnaceRecipes.instance().getSmeltingResult(itemstack);
            ItemStack itemstack2 = furnaceItemStacks.get(2);
            if (itemstack2.isEmpty())
                furnaceItemStacks.set(2, itemstack1.copy());
            else if (itemstack2.getItem() == itemstack1.getItem())
                itemstack2.grow(itemstack1.getCount());
            if (itemstack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && itemstack.getMetadata() == 1 && !furnaceItemStacks.get(1).isEmpty() && furnaceItemStacks.get(1).getItem() == Items.BUCKET)
                furnaceItemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
            itemstack.shrink(1);
        }
    }
    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
            return 0;
        else
        {
            Item item = stack.getItem();
            if (!item.getRegistryName().getResourceDomain().equals("minecraft"))
            {
                @SuppressWarnings("deprecation")
                int burnTime = net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
                if (burnTime != 0) return burnTime;
            }
            return item == Item.getItemFromBlock(Blocks.WOODEN_SLAB) ? 150 : (item == Item.getItemFromBlock(Blocks.WOOL) ? 100 : (item == Item.getItemFromBlock(Blocks.CARPET) ? 67 : (item == Item.getItemFromBlock(Blocks.LADDER) ? 300 : (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON) ? 100 : (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD ? 300 : (item == Item.getItemFromBlock(Blocks.COAL_BLOCK) ? 16000 : (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()) ? 200 : (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()) ? 200 : (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()) ? 200 : (item == Items.STICK ? 100 : (item != Items.BOW && item != Items.FISHING_ROD ? (item == Items.SIGN ? 200 : (item == Items.COAL ? 1600 : (item == Items.LAVA_BUCKET ? 20000 : (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL ? (item == Items.BLAZE_ROD ? 2400 : (item instanceof ItemDoor && item != Items.IRON_DOOR ? 200 : (item instanceof ItemBoat ? 400 : 0))) : 100)))) : 300)))))))))));
        }
    }
    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }
    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    @Override
	public boolean isUsableByPlayer(EntityPlayer player)
    {
        return world.getTileEntity(pos) != this ? false : player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
    }
    @Override
	public void openInventory(EntityPlayer player)
    {
    }
    @Override
	public void closeInventory(EntityPlayer player)
    {
    }
    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    @Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if (index == 2)
            return false;
        else if (index != 1)
            return true;
        else
        {
            ItemStack itemstack = furnaceItemStacks.get(1);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
        }
    }
    @Override
	public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? SLOTS_BOTTOM : (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES);
    }
    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    @Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return isItemValidForSlot(index, itemStackIn);
    }
    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    @Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();
            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
                return false;
        }
        return true;
    }
    @Override
	public String getGuiID()
    {
        return "minecraft:furnace";
    }
    @Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerFurnace(playerInventory, this);
    }
    @Override
	public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return furnaceBurnTime;
            case 1:
                return currentItemBurnTime;
            case 2:
                return cookTime;
            case 3:
                return totalCookTime;
            default:
                return 0;
        }
    }
    @Override
	public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                furnaceBurnTime = value;
                break;
            case 1:
                currentItemBurnTime = value;
                break;
            case 2:
                cookTime = value;
                break;
            case 3:
                totalCookTime = value;
        }
    }
    @Override
	public int getFieldCount()
    {
        return 4;
    }
    @Override
	public void clear()
    {
        furnaceItemStacks.clear();
    }
    IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
    IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
    IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);
    @SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}