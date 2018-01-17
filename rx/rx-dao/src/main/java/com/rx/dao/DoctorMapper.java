package com.rx.dao;

import java.util.List;
import java.util.Map;

import com.rx.entity.Doctor;
import tk.mybatis.mapper.common.Mapper;

public interface DoctorMapper extends Mapper<Doctor> {
	public List<Map<String,Object>> getDoctorByPatientId(long patient_id); 
}