package com.fehead.luckymoney.properties;

import com.fehead.luckymoney.domain.Luckymoney;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by xiaoaxiao on 2019/5/20
 * Description:
 */
public interface LuckymoneyRepository extends JpaRepository<Luckymoney,Integer> {
}
