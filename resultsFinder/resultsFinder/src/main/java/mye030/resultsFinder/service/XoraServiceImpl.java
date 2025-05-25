package mye030.resultsFinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mye030.resultsFinder.datamodel.Xores;
import mye030.resultsFinder.mappers.XoresMapper;

@Service
public class XoraServiceImpl implements XoraService {
	
	@Autowired
	private XoresMapper xoraMapper;

	@Override
	public Xores findXoresByIsoCode(int ISO_code) {
		return xoraMapper.findByIsoCode(ISO_code);
	}
	
}
