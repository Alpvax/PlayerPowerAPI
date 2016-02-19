package alpvax.powerproviders.api.provider.item;

import alpvax.powerproviders.api.provider.IPowerProvider;
import alpvax.powerproviders.api.provider.item.EntityItemPP;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPowerProvider extends Item implements IPowerProvider
{
	public boolean hasCustomEntity(ItemStack stack)
	{
		return true;
	}

	public Entity createEntity(World world, Entity location, ItemStack itemstack)
	{
		return new EntityItemPP(world, location.posX, location.posY, location.posZ, itemstack, this);
	}
}
