package com.niufennan.jtodos.persistence;

import com.niufennan.jtodos.models.DictionaryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryItemRepository  extends JpaRepository<DictionaryItem,Integer> {
    List<DictionaryItem> findByDicname(String dicName);
    List<DictionaryItem> findByTypevalue(String typeValue);
    List<DictionaryItem> findByTypevalueOrderBySort(String typeValue);
}
