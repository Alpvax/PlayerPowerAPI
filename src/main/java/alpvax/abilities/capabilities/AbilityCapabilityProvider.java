package alpvax.abilities.capabilities;

import alpvax.abilities.api.ability.IAbilityProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class AbilityCapabilityProvider<P> implements ICapabilitySerializable<NBTTagCompound>
{
	private IAbilityProvider<P> rootProvider;

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == CapabilityAbilityHandler.ABILITY_PROVIDER_CAPABILITY && CapabilityAbilityHandler.ABILITY_PROVIDER_CAPABILITY != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == CapabilityAbilityHandler.ABILITY_PROVIDER_CAPABILITY ? (T)rootProvider : null;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		// TODO Auto-generated method stub
		
	}
}
