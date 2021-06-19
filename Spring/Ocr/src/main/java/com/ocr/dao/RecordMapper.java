package com.ocr.dao;

import com.ocr.pojo.Record;

import java.util.List;

public interface RecordMapper {
    List<Record> getRecords(int userId);
    int restoreRecords(Record record);
}
