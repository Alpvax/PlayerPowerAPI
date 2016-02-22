package alpvax.powerproviders.api.effect;

public interface IParameter
{
	public static class IntParam implements IParameter
	{
		private int value;
		
		public void setValue(int val)
		{
			value = val;
		}
		
		public int getValue()
		{
			return value;
		}
	}
	public static class DoubleParam implements IParameter
	{
		private double value;
		
		public void setValue(double val)
		{
			value = val;
		}
		
		public double getValue()
		{
			return value;
		}
	}
	public static class Parameter<T> implements IParameter
	{

		private T value;
		
		public void setValue(T val)
		{
			value = val;
		}
		
		public T getValue()
		{
			return value;
		}
	}
}
