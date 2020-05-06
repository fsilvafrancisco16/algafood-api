package com.ffs.api.controller;

import com.ffs.api.controller.model.KitchensXMLWrapper;
import com.ffs.api.domain.model.Kitchen;
import com.ffs.api.domain.repository.KitchenRepository;
import com.ffs.api.domain.service.KitchenRegistrationService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenRegistrationService kitchenRegistrationService;

    @GetMapping
    public List<Kitchen> listAll() {
        return this.kitchenRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXMLWrapper listAllXML() {
        return new KitchensXMLWrapper(this.kitchenRepository.findAll());
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> findById(@PathVariable Long kitchenId) {
        var kitchen = this.kitchenRepository.findById(kitchenId);

        return (kitchen != null) ? ResponseEntity.ok(kitchen) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Kitchen add(@RequestBody Kitchen kitchen) {
        return this.kitchenRegistrationService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    @ResponseStatus(CREATED)
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchenParam) {
        var kitchen = this.kitchenRepository.findById(kitchenId);

        if (kitchen != null) {
            BeanUtils.copyProperties(kitchenParam, kitchen, "id");

            return ResponseEntity.ok(this.kitchenRegistrationService.save(kitchen));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(CREATED)
    public ResponseEntity<Kitchen> delete(@PathVariable Long kitchenId) {
        try {
            var kitchen = this.kitchenRepository.findById(kitchenId);

            if (kitchen != null) {
                this.kitchenRepository.delete(kitchen);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(CONFLICT).build();
        }
    }
}
