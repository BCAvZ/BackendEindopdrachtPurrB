package Novi.Student.PurrB.Services;

import Novi.Student.PurrB.Dtos.CatInputDto;
import Novi.Student.PurrB.Dtos.ClientDto;
import Novi.Student.PurrB.Exceptions.RecordNotFoundException;
import Novi.Student.PurrB.Helpers.JwtUtils;
import Novi.Student.PurrB.Models.Cat;
import Novi.Student.PurrB.Models.Client;
import Novi.Student.PurrB.Dtos.CatDto;
import Novi.Student.PurrB.Repositories.CatRepository;
import Novi.Student.PurrB.Repositories.ClientRepository;
import Novi.Student.PurrB.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
@Service
public class CatService {

    private CatRepository catRepos;

    @Autowired
    private ClientRepository clientRepos;

    @Autowired
    private  JwtService jwtService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    public CatService(CatRepository catRepos) {
        this.catRepos = catRepos;
    }

    public List<CatDto> getCatsByAuth(@RequestHeader("Authorization") String authHeader){
        String jwtToken = authHeader.substring(7);
        String username = jwtService.extractUsername(jwtToken);

        Client c = clientRepos.findByUser_Username(username);

        return convertCatsToCatDtos(c.getCats());
    }

    public CatDto postCat(@RequestHeader("Authorization") String authHeader, CatInputDto catInput){

        String jwtToken = authHeader.substring(7);
        String username = jwtService.extractUsername(jwtToken);

        Client c = clientRepos.findByUser_Username(username);

        Cat a = catToModel(catInput);

        a.setOwner(c);
        catRepos.save(a);

        return catToDto(a);
    }

    public CatDto editCat (@RequestHeader("Authorization") String authHeader, Long id, CatDto catInput){

        Client c = clientRepos.findByUser_Username(jwtUtils.extractUsernameFromToken(authHeader));

        List<Cat> cats = c.getCats();

        for (Cat cat : cats) {
            if (cat.getCatId() == (id)) {

                cat.setDob(catInput.dob);
                cat.setName(catInput.name);
                cat.setRace(catInput.race);
                cat.setMedicalDetails(catInput.medicalDetails);
                cat.setDiet(catInput.diet);
                cat.setAge(catInput.age);

                catRepos.save(cat);

                return catToDto(cat);
            }

        else {
            throw new RecordNotFoundException("Wrong Cat ID, check your client/me page for your ID.");
        }
     }
        throw new RecordNotFoundException("Wrong Cat ID, check your client/me page for your ID.");
    }

    public void removeCat(@RequestHeader("Authorization") String authHeader, Long id){

        Client c = clientRepos.findByUser_Username(jwtUtils.extractUsernameFromToken(authHeader));

        List<Cat> cats = c.getCats();

        for (Cat cat : cats) {
            if (cat.getCatId() == (id)) {
                catRepos.delete(cat);
            }

            else {
                throw new RecordNotFoundException("Wrong Cat ID, check your client/me page for your ID.");
            }
    }}



    public List<CatDto> convertCatsToCatDtos(List<Cat> cats) {
        List<CatDto> catDtos = new ArrayList<>();
        for (Cat cat : cats) {
            CatDto catDto = new CatDto();
            catDto.catId = cat.getCatId();
            catDto.name = cat.getName();
            catDto.dob = cat.getDob();
            catDto.age = cat.getAge();
            catDto.race = cat.getRace();
            catDto.diet = cat.getDiet();
            catDto.medicalDetails = cat .getMedicalDetails();
            catDto.owner = convertClientToClientDto(cat.getOwner());
            catDtos.add(catDto);
        }
        return catDtos;
    }


    public CatDto catToDto(Cat cat){
        CatDto c = new CatDto();

        ClientDto a = convertClientToClientDto(cat.getOwner());

        c.owner = a;
        c.diet = cat.getDiet();
        c.name = cat.getName();
        c.catId = cat.getCatId();
        c.dob = cat.getDob();
        c.medicalDetails = cat.getMedicalDetails();
        c.age = cat.getAge();
        c.race = cat.getRace();


        return c;
    }

    public Cat catToModel(CatInputDto catInput){

        Cat cat = new Cat();

        cat.setAge(catInput.age);
        cat.setDiet(catInput.diet);
        cat.setDob(catInput.dob);
        cat.setName(catInput.name);
        cat.setRace(catInput.race);
        cat.setMedicalDetails(catInput.medicalDetails);

        return cat;
    }


    public ClientDto convertClientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();

        clientDto.clientId = client.getClientId();
        clientDto.address = client.getAddress();
        clientDto.name = client.getName();
        clientDto.email = client.getEmail();
        clientDto.phone = client.getPhone();
        return clientDto;
    }

    


}
