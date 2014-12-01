package com.lumaro.twitterfeel.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import org.springframework.stereotype.Repository;
import com.google.gson.GsonBuilder;
import com.lumaro.twitterfeel.model.Result;

@Repository
public class TwitterRepository {

	public void save( String topic, Result finalResult ) {

		Calendar now = Calendar.getInstance();
		int year = now.get( Calendar.YEAR );
		int month = now.get( Calendar.MONTH );
		int day = now.get( Calendar.DAY_OF_MONTH );
		int hour = now.get( Calendar.HOUR_OF_DAY );
		int minutes = now.get( Calendar.MINUTE );
		int seconds = now.get( Calendar.SECOND );

		Double random = Math.random() * 1000;

		File file = new File("/home/ec2-user/lumaro-searches/"+year+"/"+month+"/"+day+"/"+topic+"-"+hour+"."+minutes+"."+seconds+"_"+random.intValue()+".txt");

		File directory = new File( file.getParentFile().getAbsolutePath() );
		if( !directory.exists() ) {
			directory.mkdirs();
		}

		if( !file.exists() ) {
			try {
				boolean success = file.createNewFile();
				if( success ) {

					String jsonList = new GsonBuilder().create().toJson( finalResult );
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(jsonList);
					bw.close();
				}
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
	}

}
