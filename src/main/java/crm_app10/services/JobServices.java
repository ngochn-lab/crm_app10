package crm_app10.services;

import java.util.List;

import crm_app10.repository.JobRepository;
import entity.Jobs;

public class JobServices {
	
	private JobRepository jobRepository = new JobRepository();
	
	public List<Jobs> getAllJobs() {
		return jobRepository.findAll();
	}
}
