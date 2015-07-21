package alpvax.playerpowers.api.item;

import java.util.HashMap;
import java.util.Map;

import alpvax.playerpowers.api.power.IPower;
import alpvax.playerpowers.api.provider.IPowerProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IItemPowerProvider
{
	/**
	 * Called when the item is picked up.<br>
	 * Use it to activate continuous powers.
	 * @param provider the IPowerProvider attached to the ItemStack
	 * @param pp the stack that was moved
	 */
	public void itemAdded(Provider provider, EntityPlayer player);
	
	public String itemKey();
	
	public IItemPowerProvider.Provider getProviderFromStack(ItemStack stack);
	
	public class Provider implements IPowerProvider
	{
		private Map<String, IPower> powers = new HashMap<String, IPower>();
		private ItemStack itemStack;

		public Provider(){}
		public Provider(ItemStack stack)
		{
			itemStack = stack;
		}
		
		@Override
		public Map<String, IPower> getPowers()
		{
			return powers;
		}

		@Override
		public boolean persistAcrossDeath()
		{
			return true;//Persist until detached (dropped or unequipped)
		}

		@Override
		public IPowerProvider readFromNBT(NBTTagCompound compound)
		{
			return null;
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound compound)
		{
			//Empty as data is saved to the ItemStack
			return null;
		}

		public void readPowersFromNBT()
		{
			//TODO:Read
		}

		public void writePowersToNBT()
		{
			//TODO:Write
		}

		@Override
		public void onAttach(EntityPlayer player)
		{
			((IItemPowerProvider)itemStack.getItem()).itemAdded(this, player);
		}
	}
}
