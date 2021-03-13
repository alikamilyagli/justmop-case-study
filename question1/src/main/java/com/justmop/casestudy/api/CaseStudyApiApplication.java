package com.justmop.casestudy.api;

import com.justmop.casestudy.api.entity.sql.Booking;
import com.justmop.casestudy.api.entity.sql.Cleaner;
import com.justmop.casestudy.api.entity.sql.Company;
import com.justmop.casestudy.api.entity.enums.BookingType;
import com.justmop.casestudy.api.repository.BookingRepository;
import com.justmop.casestudy.api.service.CleanerService;
import com.justmop.casestudy.api.service.CompanyService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootApplication(scanBasePackages = {"com.justmop.casestudy.api"})
@EnableJpaRepositories("com.justmop.casestudy.api")
@EnableJpaAuditing
public class CaseStudyApiApplication {

	@Autowired
	CompanyService companyService;

	@Autowired
	CleanerService cleanerService;

	@Autowired
	BookingRepository bookingRepository;

	public static void main(String[] args) {
		SpringApplication.run(CaseStudyApiApplication.class, args);
	}

	/**
	 * loads some dummy data for playground
	 * @return
	 */
	@Bean
	InitializingBean loadDemo() {

		//exit if data exists
		if (companyService.count() > 0) {
			return () -> {};
		}

		ArrayList<Company> dummyCompanies = new ArrayList<>();
		dummyCompanies.add(new Company(1L, "Company One", LocalTime.of(8,0), LocalTime.of(22,00)));
		dummyCompanies.add(new Company(2L, "Company Two", LocalTime.of(8,0), LocalTime.of(22,00)));
		dummyCompanies.add(new Company(3L, "Company Three", LocalTime.of(8,0), LocalTime.of(22,00)));

		return () -> {
			Company company;
			for (int i = 0; i < dummyCompanies.size(); i++) {

				company = dummyCompanies.get(i);
				companyService.save(company);

				Cleaner cleaner;
				for (int j = 0; j < 10; j++) {
					cleaner = new Cleaner();
					cleaner.setCompany(company);
					cleaner.setFirstName("Cleaner" + ((j + (i * 10)) + 1));
					cleaner.setLastName("LName" + ((j + (i * 10)) + 1));
					cleaner.setHoliday(DayOfWeek.FRIDAY);
					cleaner.setWorkingHourStart(LocalTime.of(8,0));
					cleaner.setWorkingHourEnd(LocalTime.of(22,0));
					cleanerService.save(cleaner);
				}
			}

			Cleaner cleaner1 = cleanerService.findById(1L).get();
			Cleaner cleaner2 = cleanerService.findById(2L).get();
			Cleaner cleaner3 = cleanerService.findById(13L).get();

			Booking booking1 = new Booking(
					BookingType.TWO_HOURS,
					LocalDate.now().plusDays(1),
					LocalTime.of(12, 0,0),
					cleaner1,
					cleaner2);
			bookingRepository.save(booking1);

			Booking booking2 = new Booking(
					BookingType.FOUR_HOURS,
					LocalDate.now().plusDays(1),
					LocalTime.of(18, 0,0),
					cleaner2,
					cleaner3);
			bookingRepository.save(booking2);

		};
	}

}
