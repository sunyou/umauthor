package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.umauthor.server.model.UmAreas;

public interface AreasSV {
	
	public List<UmAreas> getUmAreasByParent(String parentid);
	
	public List<UmAreas> getProvice();
}
