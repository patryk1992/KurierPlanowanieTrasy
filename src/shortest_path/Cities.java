package shortest_path;

import java.util.Random;

public  class Cities {

public static final String[] cities = {"POZ", "WAW", "SZC", "WRO", "GDA","LUB", "KRK"};


	
public static String getRandomCity()
{
Random rand =  new Random();

return cities[rand.nextInt(7)];
}
	
	
}
