package alpvax.powerproviders.capability;

import alpvax.powerproviders.api.provider.IPowerProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PoweredObjectCapability
{
    @CapabilityInject(IPowerProvider.class)
    public static Capability<IPowerProvider> POWER_PROVIDER_CAPABILITY = null;
}
