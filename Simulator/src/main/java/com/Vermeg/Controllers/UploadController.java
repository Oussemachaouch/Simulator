package com.Vermeg.Controllers;
 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.Vermeg.dao.SimulationParametersRepository;
import com.Vermeg.services.ExtractionDataService;
import com.Vermeg.services.ProjectionDataService;
import com.Vermeg.services.StorageService;
import com.Vermeg.simulator.Simulator;
 
@RestController

public class UploadController {
 
	@Autowired
	StorageService storageService;
	@Autowired
	private Simulator simulator;
 
	@Autowired
	ProjectionDataService projectionDataService;
	
	@Autowired
	private SimulationParametersRepository simulationParameterssRepository;
	
	List<String> files = new ArrayList<String>();
 
	@PostMapping("/post")
	public List<Map<String,Double>> handleFileUpload(@RequestParam("file") MultipartFile file,@RequestParam(name="mc")long mc) {
//		String message = "";
//		try {
//			storageService.store(file);
//			files.add(file.getOriginalFilename());
// 
//			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
//			return ResponseEntity.status(HttpStatus.OK).body(message);
//		} catch (Exception e) {
//			message = "FAIL to upload " + file.getOriginalFilename() + "!";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//		}
		
		ExtractionDataService extraction = new ExtractionDataService();
		List<Map<String,Double>> solifeprojectionsresults  =projectionDataService.getData(file,simulationParameterssRepository.findbyId(mc));
		List<Map<String,Double>> simulatorprojectionsresults = simulator.simulatorAvgResults(extraction.initialComputedDataConfig(),simulationParameterssRepository.findbyId(mc));
	
		return 	simulator.comparator(solifeprojectionsresults,simulatorprojectionsresults);	

	
	}
 
	@GetMapping("/getallfiles")
	public ResponseEntity<List<String>> getListFiles(Model model) {
		List<String> fileNames = files
				.stream().map(fileName -> MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());
 
		return ResponseEntity.ok().body(fileNames);
	}
 
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
