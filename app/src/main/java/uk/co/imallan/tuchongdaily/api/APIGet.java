package uk.co.imallan.tuchongdaily.api;

import retrofit.http.GET;
import retrofit.http.Query;
import uk.co.imallan.tuchongdaily.api.data.DataPosts;

/**
 * Created by allan on 15/1/30.
 */
public interface APIGet {

	@GET("/post")
	public Response<DataPosts> getPosts(@Query("secret") String secretToken,
	                                    @Query("skip") int skip,
	                                    @Query("limit") int limit);

}
