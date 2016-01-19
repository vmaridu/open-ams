package org.openams.controller;

import java.util.HashMap;
import java.util.Map;

import org.jsondoc.core.annotation.ApiAuthBasic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@ApiAuthBasic
@RestController
@RequestMapping("/dummy")
public class DummyController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Map<String,String> getTestData() {
		
		Map<String,String> result = new HashMap<String, String>();
		result.put("Name ", "Jack the Ripper");
		result.put("City", "London");
		result.put("Occupation", "Bad Guy");
		
		return result;
	}
	
}
