import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class APIInterface {
    private static String strry ="{\"RecipeID\":59859,\"Title\":\"South St Louis Deep Gooey Butter Cake\",\"Description\":\"\",\"Cuisine\":\"American\",\"Category\":\"Desserts\",\"Subcategory\":\"Cakes\",\"StarRating\":4.38888888888889,\"WebURL\":\"http://www.bigoven.com/recipe/59859/South-St-Louis-Deep-Gooey-Butter-Cake\",\"ImageURL\":\"http://mda.bigoven.com/pics/south-st-louis-deep-gooey-butter-ca-10.jpg\",\"ReviewCount\":21,\"MedalCount\":2,\"FavoriteCount\":977,\"Poster\":{\"UserID\":0,\"UserName\":null,\"ImageURL48\":\"http://www.bigoven.com/images/avatar-nopicture48.png\",\"IsPremium\":false},\"Ingredients\":[{\"IngredientID\":1655916,\"DisplayIndex\":1,\"IsHeading\":true,\"Name\":\"Bottom Layer\",\"Quantity\":1,\"DisplayQuantity\":\"\",\"Unit\":\"\",\"MetricQuantity\":0,\"MetricDisplayQuantity\":\"\",\"MetricUnit\":\"  \",\"PreparationNotes\":\"\",\"IngredientInfo\":null,\"IsLinked\":false},{\"IngredientID\":1655917,\"DisplayIndex\":2,\"IsHeading\":false,\"Name\":\"yellow cake mix\",\"Quantity\":1,\"DisplayQuantity\":\"1\",\"Unit\":\"box\",\"MetricQuantity\":1,\"MetricDisplayQuantity\":\"1\",\"MetricUnit\":\"box\",\"PreparationNotes\":\"\",\"IngredientInfo\":{\"Name\":\"yellow cake mix\",\"Department\":\"Condiments\"},\"IsLinked\":true},{\"IngredientID\":1655918,\"DisplayIndex\":3,\"IsHeading\":false,\"Name\":\"Butter\",\"Quantity\":0.5,\"DisplayQuantity\":\"1/2\",\"Unit\":\"cup\",\"MetricQuantity\":118,\"MetricDisplayQuantity\":\"118\",\"MetricUnit\":\"ml\",\"PreparationNotes\":\"melted\",\"IngredientInfo\":{\"Name\":\"Butter\",\"Department\":\"Dairy\"},\"IsLinked\":true},{\"IngredientID\":1655919,\"DisplayIndex\":4,\"IsHeading\":false,\"Name\":\"Egg\",\"Quantity\":2,\"DisplayQuantity\":\"2\",\"Unit\":\"\",\"MetricQuantity\":2,\"MetricDisplayQuantity\":\"2\",\"MetricUnit\":\"  \",\"PreparationNotes\":\"\",\"IngredientInfo\":{\"Name\":\"Egg\",\"Department\":\"Dairy\"},\"IsLinked\":true},{\"IngredientID\":1655920,\"DisplayIndex\":5,\"IsHeading\":true,\"Name\":\"For Topping\",\"Quantity\":1,\"DisplayQuantity\":\"\",\"Unit\":\"\",\"MetricQuantity\":0,\"MetricDisplayQuantity\":\"\",\"MetricUnit\":\"  \",\"PreparationNotes\":\"\",\"IngredientInfo\":null,\"IsLinked\":false},{\"IngredientID\":1655921,\"DisplayIndex\":6,\"IsHeading\":false,\"Name\":\"Cream cheese\",\"Quantity\":8,\"DisplayQuantity\":\"8\",\"Unit\":\"ounces\",\"MetricQuantity\":227,\"MetricDisplayQuantity\":\"227\",\"MetricUnit\":\"g\",\"PreparationNotes\":\"\",\"IngredientInfo\":{\"Name\":\"Cream cheese\",\"Department\":\"Cheeses\"},\"IsLinked\":false},{\"IngredientID\":1655922,\"DisplayIndex\":7,\"IsHeading\":false,\"Name\":\"powdered sugar\",\"Quantity\":1,\"DisplayQuantity\":\"1\",\"Unit\":\"box\",\"MetricQuantity\":1,\"MetricDisplayQuantity\":\"1\",\"MetricUnit\":\"box\",\"PreparationNotes\":\"\",\"IngredientInfo\":{\"Name\":\"powdered sugar\",\"Department\":\"Baking\"},\"IsLinked\":false},{\"IngredientID\":1655923,\"DisplayIndex\":8,\"IsHeading\":false,\"Name\":\"Egg\",\"Quantity\":2,\"DisplayQuantity\":\"2\",\"Unit\":\"\",\"MetricQuantity\":2,\"MetricDisplayQuantity\":\"2\",\"MetricUnit\":\"  \",\"PreparationNotes\":\"beaten\",\"IngredientInfo\":{\"Name\":\"Egg\",\"Department\":\"Dairy\"},\"IsLinked\":true}],\"Instructions\":\"Mix ingredients for bottom layer and put into a greased and floured 9x13  pan or two eight inch pans. Mix and spread topping over bottom layer in  pan.    Sprinkle with additional powdered sugar. Bake 40 to 45 minutes at 350  Posted to MC-Recipe Digest by KateyKC~~at;aol.com on Feb 16, 1998\",\"YieldNumber\":1,\"YieldUnit\":\"Servings\",\"TotalMinutes\":0,\"ActiveMinutes\":0,\"NutritionInfo\":{\"SingularYieldUnit\":\"1 Serving (931g)\",\"TotalCalories\":4092,\"TotalFat\":262.62592,\"CaloriesFromFat\":2364,\"TotalFatPct\":3.5016789333333334,\"SatFat\":131.665616,\"SatFatPct\":6.5832808,\"MonoFat\":79.874112,\"PolyFat\":31.706816000000003,\"TransFat\":19.379376000000008,\"Cholesterol\":1344.56,\"CholesterolPct\":4.1371076923076924,\"Sodium\":4899.952,\"SodiumPct\":1.6896386206896552,\"Potassium\":735.808,\"PotassiumPct\":0.19363368421052632,\"TotalCarbs\":395.30031999999994,\"TotalCarbsPct\":1.162648,\"DietaryFiber\":5.5440001201629636,\"DietaryFiberPct\":0.22176000480651856,\"Sugar\":389.75631987983695,\"Protein\":49.2672,\"ProteinPct\":0.70381714285714292},\"IsPrivate\":null,\"CreationDate\":\"\\/Date(1072936800000)\\/\",\"LastModified\":\"\\/Date(1331253222927)\\/\",\"IsBookmark\":false,\"BookmarkURL\":null,\"BookmarkSiteLogo\":\"\",\"BookmarkImageURL\":null,\"IsRecipeScan\":null}";

    // return dummy data for a bit.
/*	public static BigOvenRecipe getRecipe()
	{
    	Gson gb = new Gson();
    	BigOvenRecipe gg=gb.fromJson(strry, BigOvenRecipe.class);
    	return gg;
	}*/
	public static  BigOvenRecipe getRecipeFromIngredient(String ing)
	{
		String url="http://api.bigoven.com/recipes?any_kw="+ing+"&api_key=axV15293h59oU9Z853fw48CmI1H1Js&pg=1&rpp=50";
		String ret="";
		try {
			URLConnection urlConnection = new URL(url).openConnection();
			
			urlConnection.setRequestProperty("Accept", "application/json");
			HttpURLConnection connection = (HttpURLConnection) urlConnection;
	        
	        connection.setRequestMethod("GET");
	        
	        InputStream is =connection.getInputStream();
	        int canRead=is.available();
        	byte[] a = new byte[canRead];
        	is.read(a, 0, canRead);

        	ret= new String(a);
	        if(ret.equals("{\"StatusCode\":400,\"Message\":\"API rate limit exceeded. Maximum requests per hour: 100.\",\"Data\":null}"))
	        	return null;
		} catch (MalformedURLException e) {
			if (ret.length()<105)
				return null;
		} catch (IOException e) {
			if (ret.length()<105)
				return null;
		}
		
		return null;
	}
	public static Image getImage(BigOvenRecipe rec)
	{	
		String url = rec.ImageURL;
		Image ret=null;
	
			URLConnection urlConnection;
			try {
				urlConnection = new URL(url).openConnection();
			HttpURLConnection connection = (HttpURLConnection) urlConnection;
	        

			connection = (HttpURLConnection) urlConnection;
	        
	        connection.setRequestMethod("GET");
	        InputStream is =connection.getInputStream();
	        byte[] kw=new byte[33];
	        (new Random()).nextBytes(kw);
	        String strs= new String(kw);
	        ret=new Image(is,strs , false);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SlickException e) {
				e.printStackTrace();
			}

	        return ret;
	}

	public static BigOvenRecipe getRandomRecipe()
	{	
    	Random r= new Random();
    	int el = r.nextInt(122);
		String url = "http://web.mit.edu/~bagre/Public/Taste/"+el;
    	Gson gb = new Gson();
    	BigOvenRecipe gg = null;
		String ret="";
		try {
		
				URLConnection urlConnection = new URL(url).openConnection();
				HttpURLConnection connection = (HttpURLConnection) urlConnection;
		        

				connection = (HttpURLConnection) urlConnection;
		        
		        connection.setRequestMethod("GET");
		        InputStream is =connection.getInputStream();
		        try {
					Thread.sleep(100);
				
		        int canRead=is.available();
		        byte[] a;
		        ret="";
		        while(is.available()>0)
		        {
			        canRead=is.available();
			        a = new byte[canRead];
		        	is.read(a,0,canRead);
		        	ret=ret+new String(a);
			        Thread.sleep(100);
		        }
		        } catch (InterruptedException e) {
					e.printStackTrace();
				}
		    	gg=gb.fromJson(ret, BigOvenRecipe.class);
	        	
	        	
	} catch (MalformedURLException e) {
		if (ret.length()<105)
			ret=strry;
	} catch (IOException e) {
		if (ret.length()<105)
			ret=strry;
	}catch (JsonSyntaxException e)
	{
		System.out.print("\n\n\n\n\n\n");
		System.out.println(ret);
		System.out.print("\n\n\n\n\n\n");
		ret=strry;
	}
		if (gg==null)
			gg=gb.fromJson(ret, BigOvenRecipe.class);
		gg.initDisp();
        return gg;
	}
}
