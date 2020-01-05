package fragment.sequence.service;

import java.math.BigInteger;

import fragment.sequence.exception.SequenceException;


public interface SequenceGenService {
    BigInteger nextVal(String sequenceName) throws SequenceException;
}
