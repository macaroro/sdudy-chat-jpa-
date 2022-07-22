package com.ezen.demo.appctx;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgRepository extends JpaRepository<Message, Integer>
{

}
