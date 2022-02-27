package study.springmvc.itemservice.web.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.springmvc.itemservice.domain.item.Item;
import study.springmvc.itemservice.domain.item.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping(value = "/basic/items")
@RequiredArgsConstructor
public class basicItemController {

    private final ItemRepository itemRepository;

    /**
     * 상품 목록
     * @param model
     * @return
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     * 상품 상세
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping(value = "/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * 상품 등록 폼
     * @return
     */
    @GetMapping(value = "/add")
    public String addForm() {
        return "basic/addForm";
    }

    /**
     * 상품 등록 v1
     * @param itemName
     * @param price
     * @param quantity
     * @param model
     * @return
     */
    //@PostMapping(value = "/add")
    public String addItemV1(@RequestParam("itemName") String itemName,
                       @RequestParam("price") Integer price,
                       @RequestParam("quantity") Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    /**
     * 상품 등록 v2
     * @param item
     * @return
     */
    //@PostMapping(value = "/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
        //model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * 상품 등록 v3
     * @param item
     * @return
     */
    //@PostMapping(value = "/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * 상품 등록 v4
     * @param item
     * @return
     */
    @PostMapping(value = "/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void inite() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
