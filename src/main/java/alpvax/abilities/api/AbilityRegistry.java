package alpvax.abilities.api;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;

import alpvax.abilities.api.provider.IAPTemplate;

public enum AbilityRegistry
{
	INSTANCE;

	
	
	//*************PROVIDERS********************
	@SuppressWarnings("serial")private class Registry<T> extends HashMap<String, IAPTemplate<T>>{}
	
	private Map<Class<?>, Registry<?>> registries = new HashMap<>();

	public <T> void registerProvider(Class<T> providerType, String category, String key, IAPTemplate<T> providerTemplate)
	{
		registerProvider(providerType, category + "." + key, providerTemplate);
	}
	@SuppressWarnings("unchecked")
	public <T> void registerProvider(Class<T> providerType, String key, IAPTemplate<T> providerTemplate)
	{
		if(!registries.containsKey(providerType))
		{
			registries.put(providerType, new Registry<T>());
		}
		((Registry<T>)registries.get(providerType)).put(key, providerTemplate);
	}
	
	public <T> IAPTemplate<T> getProvider(Class<T> providerType, String category, String key)
	{
		return getProvider(providerType, category + "." + key);
	}
	@SuppressWarnings("unchecked")
	public <T> IAPTemplate<T> getProvider(Class<T> providerType, String key)
	{
		if(!registries.containsKey(providerType))
		{
			return null;
		}
		return ((Registry<T>)registries.get(providerType)).get(key);
	}
	
	
	//*************EFFECTS********************
	private static Map<String, IAbilityEffect<?>> effects = new HashMap<>();
	
	public static void registerEffect(String name, IAbilityEffect<?> effect)
	{
        if (Strings.isNullOrEmpty(name))
        {
            throw new IllegalArgumentException("Attempted to register an effect with no name: " + effect);
        }
        assert effect != null : "registerEffect: effect cannot be null";
        effects.put(name, effect);
	}
	
	public static IAbilityEffect<?> getEffect(String name)
	{
		return effects.get(name);
	}
}
