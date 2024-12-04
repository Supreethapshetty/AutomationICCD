package FrameWorkSeleniumDesign.JsonData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.devtools.v128.filesystem.model.File;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class DataReader {

	// utilities
	public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException {
		// json to string
		String Jsondata = FileUtils.readFileToString(new File(path),StandardCharsets.UTF_8);

		// string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =mapper.readValue(Jsondata,
				new TypeReference<List<HashMap<String, String>>>(){});
return data;
	}
}
