package Novi.Student.PurrB.Controllers;

import Novi.Student.PurrB.Dtos.CatDto;
import Novi.Student.PurrB.Dtos.CatInputDto;
import Novi.Student.PurrB.Repositories.UserRepository;
import Novi.Student.PurrB.Services.CatService;
import Novi.Student.PurrB.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("cats")
public class CatController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("")
    public ResponseEntity<List<CatDto>> getCatByAuth(@RequestHeader("Authorization") String authHeader){
        return ResponseEntity.ok().body(catService.getCatsByAuth(authHeader));
    }

    @PostMapping("")
    public ResponseEntity<CatInputDto> postCat(@RequestHeader("Authorization") String authHeader, @RequestBody CatInputDto catInput){
        catService.postCat(authHeader, catInput);

        return ResponseEntity.created(null).body(catInput);
    }

    @PutMapping("{id}")
    public ResponseEntity<CatDto> editCat(@RequestHeader("Authorization") String autHeader, @PathVariable Long id, @RequestBody CatDto catInput) {


        return ResponseEntity.ok(catService.editCat(autHeader, id, catInput));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CatDto> removeCat (@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        catService.removeCat(authHeader, id);

        return ResponseEntity.noContent().build();
    }




}
