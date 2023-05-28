package com.web.puzzlestore.BackEnd.rest;

import java.util.ArrayList;
import java.util.List;

import com.web.puzzlestore.BackEnd.model.dtos.PuzzleDTO;
import com.web.puzzlestore.BackEnd.model.entities.Puzzle;
import com.web.puzzlestore.BackEnd.security.Roles.IsAdmin;
import com.web.puzzlestore.BackEnd.security.Roles.IsAdminOrUser;
import com.web.puzzlestore.BackEnd.service.interfaces.IPuzzleService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@IsAdmin
@RequestMapping(
    value = "/puzzles", 
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class PuzzleController {
    
    @Autowired
    private IPuzzleService puzzleService;

    @Autowired
    private ModelMapper modelMapper;

    //================= CRUD METHODS =================//

    @IsAdminOrUser
    @RequestMapping(
        value = "/{page}/{size}",
        method = RequestMethod.GET
    )
    public Page<PuzzleDTO> getPuzzles(@PathVariable int page, @PathVariable int size){
        Pageable pageable = new SolrPageRequest(page, size, Sort.by(Direction.ASC, "id"));
        Page<Puzzle> puzzles = puzzleService.getPuzzles(pageable);
        List<PuzzleDTO> puzzleDTOs = convertPuzzlesToDTOs(puzzles);
        return new PageImpl<>(puzzleDTOs, pageable, puzzles.getTotalElements());
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET
    )
    public PuzzleDTO getPuzzleById(@PathVariable Long id){
        return convertPuzzleToDTO(puzzleService.getPuzzleById(id));
    }

    @RequestMapping(
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public PuzzleDTO createPuzzle(@RequestBody PuzzleDTO newPuzzle){
        return convertPuzzleToDTO(puzzleService.createPuzzle(convertDTOToPuzzle(newPuzzle)));
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.PUT,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public PuzzleDTO updatePuzzle(@PathVariable Long id, @RequestBody PuzzleDTO puzzleToUpdate){
        return convertPuzzleToDTO(puzzleService.updatePuzzle(id, convertDTOToPuzzle(puzzleToUpdate)));
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.DELETE
    )
    public void deletePuzzle(@PathVariable Long id){
        this.puzzleService.deletePuzzle(id);
    }

    //================= OTHER METHODS =================//

    @IsAdminOrUser
    @RequestMapping(
        value = "/search/{page}/{size}",
        method = RequestMethod.GET
    )
    public Page<PuzzleDTO> getPuzzlesByName(@PathVariable int page, @PathVariable int size, @RequestParam(name = "search") String searchCriteria){
        Pageable pageable = new SolrPageRequest(page, size, Sort.by(Direction.ASC, "id"));
        Page<Puzzle> puzzles = puzzleService.getPuzzlesByName(pageable, searchCriteria);
        List<PuzzleDTO> puzzleDTOs = convertPuzzlesToDTOs(puzzles);
        return new PageImpl<>(puzzleDTOs, pageable, puzzles.getTotalElements());
    }

    //================= CONVERTES =================//

    private PuzzleDTO convertPuzzleToDTO(Puzzle puzzle){
        return modelMapper.map(puzzle, PuzzleDTO.class);
    }

    private Puzzle convertDTOToPuzzle(PuzzleDTO puzzleDTO){
        return modelMapper.map(puzzleDTO, Puzzle.class);
    }

    private List<PuzzleDTO> convertPuzzlesToDTOs(Page<Puzzle> puzzlesPages){
        List<PuzzleDTO> puzzleDTOs = new ArrayList<PuzzleDTO>();
        for (Puzzle puzzle : puzzlesPages) {
            puzzleDTOs.add(convertPuzzleToDTO(puzzle));
        }
        return puzzleDTOs;
    }
    
}
