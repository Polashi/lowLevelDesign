package org.example.splitwise.controller;

import org.example.splitwise.dto.*;
import org.example.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class SettleUpController {
    @Autowired
    private GroupService groupService;

    public SettleGroupResponseDto settleGroup(SettleGroupRequestDto dto) throws Exception {
        SettleGroupResponseDto responseDto = new SettleGroupResponseDto();
        Optional.ofNullable(groupService.settleGroup(dto.getGroupId())).ifPresentOrElse(list ->
                {
                    responseDto.setTransactionList(list);
                    responseDto.setResponseStatus(ResponseStatus.SUCCESS);
                },
                ()->{
                    responseDto.setResponseStatus(ResponseStatus.FAILURE);
                }
        );
        return responseDto;
    }

    public SettleUserResponseDto settleUser(SettleUserRequestDto requestDto){

        return null;
    }
}
