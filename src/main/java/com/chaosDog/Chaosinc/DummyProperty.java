//this class is based off of CJMinecraft's example mod
//https://github.com/CJMinecraft01/BitOfEverything
package com.chaosDog.Chaosinc;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.properties.PropertyHelper;

import java.util.Collection;

public class DummyProperty extends PropertyHelper<Boolean> {
	protected DummyProperty(String name) {
		super(name, Boolean.class);
	}
	@Override
	public Collection<Boolean> getAllowedValues() {
		return ImmutableSet.<Boolean>of(Boolean.valueOf(false));
	}

	@Override
	public Optional<Boolean> parseValue(String value) {
		return Optional.<Boolean>of(Boolean.valueOf(false));
	}

	@Override
	public String getName(Boolean value) {
		return "false";
	}

	public static DummyProperty create(String name) {
		return new DummyProperty(name);
	}

}