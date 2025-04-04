package com.IBMirnga.jobms.job;

import com.IBMirnga.jobms.job.clients.CompanyClient;
import com.IBMirnga.jobms.job.clients.ReviewClient;
import com.IBMirnga.jobms.job.dto.JobDTO;
import com.IBMirnga.jobms.job.external.Company;
import com.IBMirnga.jobms.job.external.Review;
import com.IBMirnga.jobms.job.mapper.JobMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService{

    @Autowired
    private final JobRepository jobRepository;

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    private final CompanyClient companyClient;
    @Autowired
    private final ReviewClient reviewClient;

//    int attempt =0;

    @Override
//    @CircuitBreaker(name = "companyBreaker",
//            fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
//        System.out.println("Attempt: " + ++attempt);
        List<Job> jobs = jobRepository.findAll();
         List<JobDTO> jobDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());

//        RestTemplate restTemplate = new RestTemplate();
//
//         for (Job job : jobs) {
//             JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
//             jobWithCompanyDTO.setJob(job);
//
//             Company company = restTemplate.getForObject("http://localhost:8082/companies/" + job.getCompanyId(), Company.class);
//             jobWithCompanyDTO.setCompany(company);
//
//             jobWithCompanyDTOS.add(jobWithCompanyDTO);
//         }
    }
//
//    public List<String> companyBreakerFallback(Exception e) {
//        List<String> list = new ArrayList<>();
//        list.add("Dummy");
//        return list;
//    }

    private JobDTO convertToDto(Job job) {

//        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
//        jobWithCompanyDTO.setJob(job);

//        RestTemplate restTemplate = new RestTemplate();
//        Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/company/" + job.getCompanyId(), Company.class);

        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReview(job.getCompanyId());

//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });

//        List<Review> reviews = reviewResponse.getBody();

        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDto(job, company, reviews);

        jobDTO.setCompany(company);


        return jobDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO findJobById(Long id) {
       Job job = jobRepository.findById(id).orElse(null);
       return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
      try {
          jobRepository.deleteById(id);
          return true;
      } catch (Exception e) {
          return false;
      }
    }


    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isPresent()) {
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        return false;
    }
}
