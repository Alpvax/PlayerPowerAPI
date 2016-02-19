package alpvax.powerproviders.api.entity;

import alpvax.powerproviders.api.effect.IPowerEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPowerTarget extends Entity
{
	private int age;
	private IPowerEffect effect;
	
	public EntityPowerTarget(World worldIn)
	{
		super(worldIn);
        noClip = true;
        isImmuneToFire = true;
		age = 0;
	}
    public EntityPowerTarget(World worldIn, double x, double y, double z, IPowerEffect effect)
    {
    	this(worldIn);
        this.setPosition(x, y, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
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
