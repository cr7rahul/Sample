package com.example.test.mysqlapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.mysqlapi.exception.ResourceNotFoundException;
import com.example.test.mysqlapi.model.LearnModel;
import com.example.test.mysqlapi.repository.LearnModelRepository;

@RestController
@RequestMapping("/learn")
public class LearnModelController {

	@Autowired
	LearnModelRepository learnModelRepository;

	/**
	 * Create
	 */
	@PostMapping("/model")
	public LearnModel createModel(@Valid @RequestBody LearnModel learnModel) {
		return learnModelRepository.save(learnModel);
	}

	/**
	 * Get All
	 */
	@GetMapping("/model")
	public List<LearnModel> getAllModel() {
		return learnModelRepository.findAll();
	}

	/**
	 * Get One
	 */
	@GetMapping("/model/{id}")
	public LearnModel getOneModel(@PathVariable(value = "id") int modelID) {
		return learnModelRepository.findById(modelID)
				.orElseThrow(() -> new ResourceNotFoundException("Model", "id", modelID));
	}

	/**
	 * Update
	 */
	@PutMapping("/model/{id}")
	public LearnModel updateModel(@PathVariable(value = "id") int modelID, @Valid @RequestBody LearnModel learnModel) {
		LearnModel model = learnModelRepository.findById(modelID)
				.orElseThrow(() -> new ResourceNotFoundException("Model", "id", modelID));
		model.setFIRST_NAME(learnModel.getFIRST_NAME());
		model.setMIDDLE_NAME(learnModel.getMIDDLE_NAME());
		model.setLAST_NAME(learnModel.getLAST_NAME());
		model.setUSERNAME(learnModel.getUSERNAME());
		model.setPASSWORD(learnModel.getPASSWORD());
		return learnModelRepository.save(model);

	}

	/**
	 * Delete
	 */
	@DeleteMapping("/model/{id}")
	public ResponseEntity<?> deleteModel(@PathVariable(value = "id") int modelID) {
		LearnModel model = learnModelRepository.findById(modelID)
				.orElseThrow(() -> new ResourceNotFoundException("Model", "id", modelID));
		learnModelRepository.delete(model);
		return ResponseEntity.ok().build();
	}

	/**
	 * Check Duplicate
	 * 
	 * @param modelID
	 * @param learnModel
	 * @return
	 */
	@GetMapping("models/{username}")
	public LearnModel checkDuplicateModel(@PathVariable(value = "username") int modelUsername) {
		 
		 Optional<LearnModel> optUser = learnModelRepository.findById(modelUsername); // returns java8 optional
		    if (optUser.isPresent()) {
		        return optUser.get();
		    } else {
		        // handle not found, return null or throw
		    }
		    return optUser.get();

	}
}
