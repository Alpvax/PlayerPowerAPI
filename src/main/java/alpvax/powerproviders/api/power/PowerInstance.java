package alpvax.powerproviders.api.power;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import alpvax.powerproviders.api.PowerRegistry;
import alpvax.powerproviders.api.effect.IPowerEffect;
import alpvax.powerproviders.api.target.EffectInstance;
import alpvax.powerproviders.api.target.EffectTemplate;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

public class PowerInstance
{
	private List<EffectInstance> effects = new ArrayList<>();
	
	public PowerInstance(List<EffectTemplate> effects)
	{
		for(EffectTemplate template : effects)
		{
			this.effects.add(new EffectInstance(template));
		}
	}

	public boolean isActive()
	{
		return false;
	}
	
	public List<IPowerEffect> getEffects()
	{
		List<IPowerEffect> list = new ArrayList<>();
		for(EffectInstance effect : effects)
		{
			list.add(effect.getEffect());
		}
		return list;
	}

	public boolean hasEffect(Predicate<IPowerEffect> filter)
	{
		for(EffectInstance effect : effects)
		{
			if(filter.apply(effect.getEffect()))
			{
				return true;
			}
		}
		return false;
	}

	public void writeToNBT(NBTTagCompound powerTag)
	{
		NBTTagList effectlist = new NBTTagList();
		for(EffectInstance e : effects)
		{
			NBTTagCompound nbt = new NBTTagCompound();
			e.writeEffectToNBT(nbt);
		}
		powerTag.setTag("Effects", effectlist);
	}

	public static PowerInstance loadItemStackFromNBT(NBTTagCompound powerTags)
	{
		NBTTagList effectList = powerTags.getTagList("Effects", NBT.TAG_COMPOUND);
		for(int i = 0; i < effectList.tagCount(); i++)
		{
			NBTTagCompound nbt = effectList.getCompoundTagAt(i);
			PowerRegistry.getEffect(name)
		}
		return null;
	}
}
