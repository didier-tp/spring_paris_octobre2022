package tp.service;

import org.springframework.stereotype.Service;

@Service
public class XyServiceImpl implements XyService {

	@Override
	public String getData() {
		return "data from XyServiceImpl";
	}

}
