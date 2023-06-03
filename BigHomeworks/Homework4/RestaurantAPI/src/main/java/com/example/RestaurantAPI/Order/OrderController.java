package com.example.RestaurantAPI.Order;

import com.example.RestaurantAPI.Order.models.*;
import com.example.RestaurantAPI.Order.services.DishService;
import com.example.RestaurantAPI.Order.services.OrderDishService;
import com.example.RestaurantAPI.Order.services.OrderService;
import com.example.RestaurantAPI.User.models.RestaurantUser;
import com.example.RestaurantAPI.User.models.Role;
import com.example.RestaurantAPI.User.models.Session;
import com.example.RestaurantAPI.User.services.SessionService;
import com.example.RestaurantAPI.User.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/order")
@Tag(name = "Заказы", description = "Работа с заказами")
public class OrderController {

    private final SessionService sessionService;
    private final DishService dishService;
    private final OrderService orderService;
    private final UserService userService;
    private final OrderDishService orderDishService;

    @Autowired
    public OrderController(SessionService sessionService, DishService dishService, OrderService orderService, UserService userService, OrderDishService orderDishService) {
        this.sessionService = sessionService;
        this.dishService = dishService;
        this.orderService = orderService;
        this.userService = userService;
        this.orderDishService = orderDishService;
    }

    @PostMapping(path = "new")
    @Operation(summary = "Создание нового заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Заказ успешно размещён"),
            @ApiResponse(responseCode = "400", description = "Один из указанных продуктов не существует или указанных продуктов не хватает на складе"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    public ResponseEntity<String> addOrder(@RequestBody RequestOrder request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>("To make orders, you need to be logged in", HttpStatus.UNAUTHORIZED);
        }
        Optional<Session> session = sessionService.getSessionByToken(request.getToken());
        Map<Integer, Integer> dishList = request.getDishList();
        if (dishService.hasInStock(dishList)) {
            RestaurantOrder order = orderService.addNewOrder(session.get().getUserID(), dishList, request.getSpecialRequests());
            return new ResponseEntity<>("Order was placed successfully! Order ID: " + order.getID(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Order can not be placed because there is not enough dishes in stock", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "info")
    @Operation(summary = "Получение информации о заказе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о заказе предоставлена"),
            @ApiResponse(responseCode = "400", description = "Указанного заказа не существует"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    public ResponseEntity<ResponseOrderInfo> getOrder(@RequestBody RequestOrderInfo request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Optional<RestaurantOrder> order = orderService.getOrderByID(request.getOrderID());
        if (order.isPresent()) {
            List<OrderDish> orderDishes = orderDishService.getOrderDishesByOrderID(request.getOrderID());
            return new ResponseEntity<>(new ResponseOrderInfo(order.get(), orderDishes), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Отмена заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ успешно отменён"),
            @ApiResponse(responseCode = "400", description = "Невозможно отменить заказ (указанного заказа не существует, указанный заказ является заказом другого пользователя или заказ уже выполнен или отменён)"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    @PatchMapping(path = "cancel")
    public ResponseEntity<String> cancelOrder(@RequestBody RequestCancelOrder request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>("To make orders, you need to be logged in", HttpStatus.UNAUTHORIZED);
        }
        Optional<Session> session = sessionService.getSessionByToken(request.getToken());
        Optional<RestaurantOrder> order = orderService.getOrderByID(request.getOrderID());
        if (order.isEmpty()) {
            return new ResponseEntity<>("Order with ID " + request.getOrderID() + " does not exist", HttpStatus.BAD_REQUEST);
        }
        Optional<RestaurantUser> user = userService.getUserByID(session.get().getUserID());
        if (user.get().getID() != order.get().getUserID()) {
            return new ResponseEntity<>("You can only cancel your orders", HttpStatus.BAD_REQUEST);
        }
        if (order.get().getStatus() == Status.FINISHED || order.get().getStatus() == Status.CANCELLED) {
            return new ResponseEntity<>("Order with " + order.get().getStatus() + " status can not be cancelled", HttpStatus.BAD_REQUEST);
        }
        orderService.changeOrderStatus(request.getOrderID(), Status.CANCELLED);
        return new ResponseEntity<>("Order was cancelled successfully!", HttpStatus.OK);
    }

    @PostMapping(path = "menu")
    @Operation(summary = "Получение меню")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Меню предоставлено"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    public ResponseEntity<List<Dish>> getMenu(@RequestBody RequestMenu request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        List<Dish> dishMenu = dishService.getMenu();
        return new ResponseEntity<>(dishMenu, HttpStatus.OK);
    }

    @PostMapping(path = "dish/create")
    @Operation(summary = "Создание блюда")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Блюдо успешно создано"),
            @ApiResponse(responseCode = "400", description = "Указаны неверные данные"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Пользователь не обладает необходимыми правами")
    })
    public ResponseEntity<String> createDish(@RequestBody RequestCreateDish request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>("To create dishes, you need to be logged in and have a role " + Role.MANAGER, HttpStatus.UNAUTHORIZED);
        }
        Optional<Session> session = sessionService.getSessionByToken(request.getToken());
        Optional<RestaurantUser> user = userService.getUserByID(session.get().getUserID());
        if (user.isPresent() && user.get().getRole() == Role.MANAGER) {
            if (request.getName().length() > 100) {
                return new ResponseEntity<>("Name can not be longer than 100 characters", HttpStatus.BAD_REQUEST);
            }
            if (request.getPrice() <= 0) {
                return new ResponseEntity<>("Price has to be a positive integer", HttpStatus.BAD_REQUEST);
            }
            if (request.getQuantity() < 0) {
                return new ResponseEntity<>("Quantity can not be negative", HttpStatus.BAD_REQUEST);
            }
            Dish dish = dishService.addNewDish(request.getName(), request.getDescription(), request.getPrice(), request.getQuantity());
            return new ResponseEntity<>("Dish was created successfully! Dish ID: " + dish.getID(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("To create dishes, you need to have a " + Role.MANAGER + " role", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "dish/read")
    @Operation(summary = "Чтение блюда")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Блюдо успешно прочитано"),
            @ApiResponse(responseCode = "400", description = "Указано несуществующее блюдо"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Пользователь не обладает необходимыми правами")
    })
    public ResponseEntity<Dish> readDish(@RequestBody RequestReadDish request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Optional<Session> session = sessionService.getSessionByToken(request.getToken());
        Optional<RestaurantUser> user = userService.getUserByID(session.get().getUserID());
        if (user.isPresent() && user.get().getRole() == Role.MANAGER) {
            Optional<Dish> dish = dishService.getDishByID(request.getDishID());
            if (dish.isPresent()) {
                return new ResponseEntity<>(dish.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(path = "dish/update")
    @Operation(summary = "Обновление блюда")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Блюдо успешно обновлено"),
            @ApiResponse(responseCode = "400", description = "Указано несуществующее блюдо или указаны неверные данные"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Пользователь не обладает необходимыми правами")
    })
    public ResponseEntity<String> updateDish(@RequestBody RequestUpdateDish request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>("To update dishes, you need to be logged in and have a role " + Role.MANAGER, HttpStatus.UNAUTHORIZED);
        }
        Optional<Session> session = sessionService.getSessionByToken(request.getToken());
        Optional<RestaurantUser> user = userService.getUserByID(session.get().getUserID());
        if (user.isPresent() && user.get().getRole() == Role.MANAGER) {
            if (dishService.getDishByID(request.getDishID()).isEmpty()) {
                return new ResponseEntity<>("Dish with ID " + request.getDishID() +  " does not exist", HttpStatus.BAD_REQUEST);
            }
            if (request.getName().length() > 100) {
                return new ResponseEntity<>("Name can not be longer than 100 characters", HttpStatus.BAD_REQUEST);
            }
            if (request.getPrice() <= 0) {
                return new ResponseEntity<>("Price has to be a positive integer", HttpStatus.BAD_REQUEST);
            }
            if (request.getQuantity() < 0) {
                return new ResponseEntity<>("Quantity can not be negative", HttpStatus.BAD_REQUEST);
            }
            dishService.updateDish(request.getDishID(), request.getName(), request.getDescription(), request.getPrice(), request.getQuantity());
            return new ResponseEntity<>("Dish was updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("To update dishes, you need to have a " + Role.MANAGER + " role", HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping(path = "dish/delete")
    @Operation(summary = "Удаление блюда")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Блюдо успешно удалено"),
            @ApiResponse(responseCode = "400", description = "Указано несуществующее блюдо"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Пользователь не обладает необходимыми правами")
    })
    public ResponseEntity<String> deleteDish(@RequestBody RequestDeleteDish request) {
        if (!sessionService.isActiveSession(request.getToken())) {
            return new ResponseEntity<>("To delete dishes, you need to be logged in and have a role " + Role.MANAGER, HttpStatus.UNAUTHORIZED);
        }
        Optional<Session> session = sessionService.getSessionByToken(request.getToken());
        Optional<RestaurantUser> user = userService.getUserByID(session.get().getUserID());
        if (user.isPresent() && user.get().getRole() == Role.MANAGER) {
            if (dishService.getDishByID(request.getDishID()).isEmpty()) {
                return new ResponseEntity<>("Dish with ID " + request.getDishID() +  " does not exist", HttpStatus.BAD_REQUEST);
            }
            dishService.removeDishByID(request.getDishID());
            return new ResponseEntity<>("Dish was deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("To delete dishes, you need to have a " + Role.MANAGER + " role", HttpStatus.FORBIDDEN);
        }
    }
}
