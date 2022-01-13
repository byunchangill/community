
package com.koreait.community;

import com.koreait.community.model.BoardCategoryEntity;
import com.koreait.community.model.SubMenuVO;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("MenuPreparer")
public class MenuPreparer implements ViewPreparer {

    @Autowired
    private CommonMapper mapper;

    private List<BoardCategoryEntity> menuList;
    private List<SubMenuVO> subMenuList;

    @Override
    public void execute(Request request, AttributeContext attributeContext) {
        if(menuList == null) {
            menuList = mapper.selMenuCategoryList();
        }
        System.out.println(" ------- Called MenuPreparer-execute method --------- ");
        attributeContext.putAttribute(Const.MENU_LIST,
                new Attribute(menuList), true);

        if(subMenuList == null) {
            subMenuList = new ArrayList<>();
            subMenuList.add(new SubMenuVO("profile", "프로필"));
            subMenuList.add(new SubMenuVO("password", "비밀번호 변경"));
        }
        attributeContext.putAttribute(Const.SUB_MENU_LIST,
                new Attribute(subMenuList), true);
    }
}