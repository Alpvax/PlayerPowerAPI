package alpvax.powerproviders.api.provider.item;

import alpvax.powerproviders.api.provider.IPowerProvider;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityItemPP extends EntityItem implements IPowerProvider
{
	//TODO implement provider

	public EntityItemPP(World worldIn, double x, double y, double z, ItemStack stack, IPowerProvider provider)
	{
		super(worldIn, x, y, z, stack);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void onUpdate()
    {
		super.onUpdate();
    }
}
