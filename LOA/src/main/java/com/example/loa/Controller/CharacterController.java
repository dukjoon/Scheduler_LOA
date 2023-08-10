package com.example.loa.Controller;

import com.example.loa.Dto.CharacterInfoDto;
import com.example.loa.Entity.CharacterInfo;
import com.example.loa.Service.CharacterService;
import com.example.loa.Service.JWTService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    private Claims jwtCheckFunc(HttpServletRequest request){
        Claims token = jwtService.checkAuthorizationHeader(request);
        if(token == null){
            System.out.println("[Error] Token is Missed");
            return null;
        }
        return token;
    }
    private JWTService jwtService = new JWTService();
    @PostMapping("/api/character/init")
    @ResponseBody
    public String initChar(HttpServletRequest request, @RequestBody List<CharacterInfoDto> characterInfoDtoList){
        // 로그인 정보 확인
        Claims token = jwtCheckFunc(request);
        // 유저 확인
        if(token == null) return null;
        Integer id = Integer.parseInt(token.get("id").toString());

        boolean isInit = characterService.init(id, characterInfoDtoList);
        if(!isInit){
            System.out.println("[Error] Init Error");
            return "Init Failed";
        }
        return "Init Success";
    }

    @GetMapping("/api/character/user/get-chars")
    @ResponseBody
    public List<CharacterInfoDto> getUserCharacters(HttpServletRequest request){
        // 유저 정보 확인
        Claims token = jwtCheckFunc(request);
        if(token == null) return null;
        Integer id = Integer.parseInt(token.get("id").toString());


        List<CharacterInfoDto> characters = characterService.getCharacterByUserId(id);
        if(characters == null) System.out.println("[Alert] No Characters");
        System.out.println("[Alert] Get Characters Success");
        return characters;
    }

}
