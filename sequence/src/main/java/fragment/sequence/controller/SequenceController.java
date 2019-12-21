package fragment.sequence.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fragment.sequence.service.SequenceGenService;

@RestController
public class SequenceController {
    @Autowired
    private SequenceGenService sequenceGenService;

    @GetMapping("/get/next/value/{sequenceName}")
    public BigInteger getNextVal(@PathVariable("sequenceName") String sequenceName) {
        return sequenceGenService.nextVal(sequenceName);
    }
}
