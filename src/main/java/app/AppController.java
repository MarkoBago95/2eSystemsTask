package app;

import java.net.BindException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class AppController {

	@Autowired
	private AppService service;


	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	@GetMapping("/list")
	public String viewHomePage(Model model) {
		List<Subscriptions> listSubs = service.listAll();
		model.addAttribute("listSubs", listSubs);
		return "listOfAll";
	}
	@RequestMapping("delete/{id}")
	public String deleteSubscription(@PathVariable(name="id") int id) {
		service.deleteSub(id);
		return "redirect:/list";
	}
	@RequestMapping("/new")
	public String createNew(Model model) {
		Subscriptions sub = new Subscriptions();
		model.addAttribute("sub", sub);
		return "new_sub";
	}
	@RequestMapping("/edit/{id}")
	public ModelAndView createNew(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView("edit_sub");
		Subscriptions sub = service.get(id);
		mav.addObject("sub", sub);
		return mav;
	}

	@RequestMapping(value="/save" ,method=RequestMethod.POST)
	public String saveSub(@ModelAttribute("sub") Subscriptions sub) {
		service.save(sub);
		return "redirect:/list";
	}
	@ExceptionHandler({SQLIntegrityConstraintViolationException.class,DataIntegrityViolationException.class,BindException.class,NumberFormatException.class})
    public String handleException() {
		return "error";
    }

	@GetMapping("/get/{id}")
	public String getEmployees(Model model,@PathVariable(name="id") int id) {
		Subscriptions sub = service.get(id);
		String icao=sub.getIcao();
		final String uri = "https://api.checkwx.com/metar/"
				+ icao + "/decoded";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("X-API-Key", "4ea6bc43309b48f5a088f6ef1c");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		String data = response.getBody();
		JSONObject jsonObject = new JSONObject(data);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject dataObject = jsonArray.getJSONObject(i);
			sub.setIcaoM(icao);
			sub.setId(id);
			sub.setActive(sub.getActive());
			sub.setIcao(icao);
			sub.setMetar_value(dataObject.getString("raw_text"));
			JSONObject station = dataObject.getJSONObject("station");
			sub.setName(station.getString("name"));
			JSONObject temp = dataObject.getJSONObject("temperature");
			sub.setTemperature(temp.getInt("celsius"));
			JSONObject wind = dataObject.getJSONObject("wind");
			sub.setWind(wind.getInt("speed_kph"));
			JSONObject visibility = dataObject.getJSONObject("visibility");
			sub.setVisibility(visibility.getString("meters"));
			JSONObject elevation = dataObject.getJSONObject("elevation");
			sub.setElevation(elevation.getInt("meters"));
			service.save(sub);
		}
		model.addAttribute("dataM", sub);
		return "dataM";
		
	}

}
