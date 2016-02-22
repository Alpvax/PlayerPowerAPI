package alpvax.powerproviders.api.entity;

import alpvax.powerproviders.api.target.EffectInstance;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPowerTarget extends Entity
{
	private int age;
	private EffectInstance effect;
	
	public EntityPowerTarget(World worldIn)
	{
		super(worldIn);
        noClip = true;
        isImmuneToFire = true;
        age = 0;
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
	}
    public EntityPowerTarget(World worldIn, double x, double y, double z, EffectInstance effect)
    {
    	this(worldIn);
        setPosition(x, y, z);
        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;
        this.effect = effect;
    }

	@Override
	protected void entityInit()
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund)
	{
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound)
	{
	}
	
	public boolean isEntityInvulnerable(DamageSource source)
    {
		return true;
    }
	
	public IBlockState getBlock()
	{
		return worldObj.getBlockState(getPosition());
	}
}
