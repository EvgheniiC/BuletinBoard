package com.evghenii;

import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;
import com.evghenii.service.AdService;
import com.evghenii.service.PersonService;
import com.evghenii.service.impl.AdServiceImpl;
import com.evghenii.service.impl.PersonServiceImpl;


public class BulletinBoardTest {


    public static void main(String[] args) {





       /* PersonService personService = new PersonServiceImpl();

        RubricService rubricService = new RubricServiceImp();

        Rubric rubricWithoutAd = new Rubric("Cars");

        Rubric rubricWithAd = new Rubric("Mobile");

        LocalDate today = LocalDate.now();

        Ad ad = new Ad("Selling", today, "Auto", new BigDecimal(1000), rubricWithAd);
        Ad ad1 = new Ad("Bey", today, "Auto", new BigDecimal(2000), rubricWithAd);
        Ad ad2 = new Ad("Сhanging", today, "Auto", new BigDecimal(2500), rubricWithAd);


        rubricService.save(rubricWithAd);
        rubricService.save(rubricWithoutAd);

        Rubric cars = rubricService.findRubricByName("Cars");


        ad.setRubric(cars);
        ad1.setRubric(cars);
        ad2.setRubric(cars);

        //cars.getAds().removeIf(a -> a.getText().equals("Buy"));

        Address address = new Address("Berlin", "berlinerStr", 15);
        Address address1 = new Address("Bremen", "BremenStr", 20);

        Person ivan = new Person("Ivan", "12345");
        ivan.addAd(ad);
        ad.setPerson(ivan);
        ivan.setAddress(address);
        address.setPerson(ivan);

        Person vasiliy = new Person("Vasia", "55555");
        vasiliy.setAddress(address1);
        address1.setPerson(vasiliy);
        vasiliy.addAd(ad2);
        ad2.setPerson(vasiliy);
        vasiliy.addAd(ad1);
        ad1.setPerson(vasiliy);

        personService.save(ivan);
        personService.save(vasiliy);*/


        /*  System.out.println(personService.findPersonById(6).getName());*/

       /* for (Person p: personService.findAll()) {
            System.out.println(p.getName());

        }*/

        //   personService.findAll();

       /*  AdService adService = new AdServiceImpl();

        System.out.println(adService.findByPrice(BigDecimal.valueOf(1000)));*///?????


        AdService adService = new AdServiceImpl();



          for (Ad ad: adService.findAdInRubricById(3)) {
            System.out.println(ad.getTitle());

        }


    }
}
