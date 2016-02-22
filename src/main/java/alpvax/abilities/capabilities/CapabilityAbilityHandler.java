package alpvax.abilities.capabilities;

import java.util.concurrent.Callable;

import alpvax.abilities.api.ability.IAbilityProvider;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityAbilityHandler
{
    @CapabilityInject(IAbilityProvider.class)
    public static Capability<IAbilityProvider> ABILITY_PROVIDER_CAPABILITY = null;
    
    public static void register()
    {
        CapabilityManager.INSTANCE.register(IAbilityProvider.class, new Capability.IStorage<IAbilityProvider>()
        {
            @Override
            public NBTBase writeNBT(Capability<IAbilityProvider> capability, IAbilityProvider instance, EnumFacing side)
            {
                /*NBTTagList nbtTagList = new NBTTagList();
                for(int i = 0; i < instance.getNumAbilities(); i++)
                {
                	PowerInstance pi = instance.getPowerInSlot(i);
                    NBTTagCompound powerTag = new NBTTagCompound();
                    powerTag.setInteger("Slot", i);
                	pi.writeToNBT(powerTag);
                    nbtTagList.appendTag(powerTag);
                }
                return nbtTagList;*/
            	return null;
            }

            @Override
            public void readNBT(Capability<IAbilityProvider> capability, IAbilityProvider instance, EnumFacing side, NBTBase base)
            {
                /*NBTTagList tagList = (NBTTagList) base;
                for (int i = 0; i < tagList.tagCount(); i++)
                {
                    NBTTagCompound powerTags = tagList.getCompoundTagAt(i);
                    int j = powerTags.getInteger("Slot");
                    instance.setPowerInSlot(j, PowerInstance.loadItemStackFromNBT(powerTags));
                }*/
            }
        }, new Callable<IAbilityProvider>()
        {
            @Override
            public IAbilityProvider call() throws Exception
            {
                return null;//TODO:URGENT:new IAbilityProvider<?>();
            }
        });
    }
}
