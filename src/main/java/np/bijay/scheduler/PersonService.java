package np.bijay.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    //Schedule to add person in every 5 second
    @Scheduled(fixedRate = 5000)
    public void addPerson(){
        PersonModel person=new PersonModel();
        person.setName("person"+new Random().nextInt(123456 ));
        personRepository.save(person);
        System.out.println("addPerson call in:"+new Date().toString());
    }

    //Schedule to retrieve person in every 15 seconds
    @Scheduled(fixedRate = 15000)
    /**
     * Implementing cron-expression
     * @Scheduled(cron ="0/15 * * * * *") in every 15 second
     * @Scheduled(cron ="0 05 00 * * ?") at 12:05 AM every day
     */
    public void retrievePerson(){
        List<PersonModel> persons=personRepository.findAll();
        System.out.println("Total records:"+persons.size());
        System.out.println("retrievePerson call in:"+new Date().toString());
    }


}
