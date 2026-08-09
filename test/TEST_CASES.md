# Test cases - Warehouse Management

## 1. Mục tiêu và cách áp dụng TDD

Các test được viết theo chu trình **Red - Green - Refactor**:

1. **Red:** chọn hành vi từ bảng bên dưới, viết assertion và chạy để thấy test thất bại nếu hành vi chưa tồn tại hoặc sai.
2. **Green:** viết lượng code production tối thiểu để test vượt qua.
3. **Refactor:** loại bỏ lặp, cải thiện tên và cấu trúc trong khi toàn bộ test vẫn xanh.

Các test hiện tại đóng vai trò regression suite cho code đã có. Khi thêm nghiệp vụ mới, cần viết test thất bại trước khi sửa `src/main/java`.

## 2. Phân vùng tương đương

| ID | Chức năng | Phân vùng | Dữ liệu đại diện | Kết quả mong đợi | Test tự động |
|---|---|---|---|---|---|
| EP-01 | Sort | Danh sách hợp lệ | 3 vật liệu | Danh sách được sắp xếp | `sortsUsingEveryDecisionCombination` |
| EP-02 | Sort | Danh sách không hợp lệ | `null` | `InvalidSortDataException` | `rejectsNullList` |
| EP-03 | Sort | Tiêu chí không hợp lệ | `null` | `InvalidSortDataException` | `rejectsNullCriterion` |
| EP-04 | Sort | Chiều không hợp lệ | `null` | `InvalidSortDataException` | `rejectsNullDirection` |
| EP-05 | Search | Chuỗi tìm kiếm không hợp lệ | `null`, `""`, khoảng trắng | `InvalidSearchDataException` | `rejectsInvalidSearchText` |
| EP-06 | Search | Danh sách không hợp lệ | `null` | `InvalidSearchDataException` | `rejectsNullList` |
| EP-07 | Search name | Từ khóa hợp lệ, khác hoa/thường | `mIlK` | Trả về cả `Fresh Milk`, `Milk Powder` | `searchesNamesCaseInsensitively` |
| EP-08 | Inventory | Vật liệu hợp lệ | Một `FoodMaterial` | Kích thước tăng 1 | `addsMaterial` |

## 3. Giá trị biên

| ID | Chức năng | Biên kiểm thử | Dữ liệu | Kết quả mong đợi | Test tự động |
|---|---|---|---|---|---|
| BV-01 | Sort | Kích thước nhỏ nhất | 0 phần tử | Không lỗi, vẫn rỗng | `handlesBoundaryListSizes` |
| BV-02 | Sort | Ngay trên biên nhỏ nhất | 1 phần tử | Không đổi | `handlesBoundaryListSizes` |
| BV-03 | Sort | Số phần tử nhỏ nhất cần hoán đổi | 2 phần tử đảo thứ tự | Được sắp đúng | `handlesBoundaryListSizes` |
| BV-04 | Binary search | Vị trí đầu | ID `A` | Tìm thấy `A` | `findsItemsAtSearchBoundaries` |
| BV-05 | Binary search | Vị trí giữa | ID `B` | Tìm thấy `B` | `findsItemsAtSearchBoundaries` |
| BV-06 | Binary search | Vị trí cuối | ID `C` | Tìm thấy `C` | `findsItemsAtSearchBoundaries` |
| BV-07 | Binary search | Ngoài miền | ID `D` | Trả về `null` | `findsItemsAtSearchBoundaries` |
| BV-08 | Search | Danh sách rỗng | 0 phần tử | Không tìm thấy | `searchesEmptyList` |
| BV-09 | ID generator | Giá trị đầu và kế tiếp | Lần gọi 1, 2 | Hậu tố `00000001`, `00000002` | `generatesIndependentSequentialIds` |
| BV-10 | Random generation | Số lượng 0 và 1 | `amount=0`, `amount=1` | Sinh tương ứng 0 và 1 phần tử | `generatesBoundaryAmounts` |

## 4. Bảng quyết định sắp xếp

Mỗi hàng được chạy với cả ba thuật toán Bubble, Selection và Insertion (18 tổ hợp).

| Rule | Criterion | Direction | Thứ tự ID mong đợi |
|---|---|---|---|
| DT-S01 | ID | ASCENDING | DA00000001, FA00000001, FA00000002 |
| DT-S02 | ID | DESCENDING | FA00000002, FA00000001, DA00000001 |
| DT-S03 | PRICE | ASCENDING | FA00000001, FA00000002, DA00000001 |
| DT-S04 | PRICE | DESCENDING | DA00000001, FA00000002, FA00000001 |
| DT-S05 | QUANTITY | ASCENDING | FA00000002, DA00000001, FA00000001 |
| DT-S06 | QUANTITY | DESCENDING | FA00000001, DA00000001, FA00000002 |

## 5. Bảng quyết định Binary Search

| Rule | List null? | ID hợp lệ? | List đã sort? | ID tồn tại? | Kết quả |
|---|---:|---:|---:|---:|---|
| DT-B01 | Có | Có | - | - | `InvalidSearchDataException` |
| DT-B02 | Không | Không | - | - | `InvalidSearchDataException` |
| DT-B03 | Không | Có | Không | - | `UnsortedDataException` |
| DT-B04 | Không | Có | Có | Có | Trả về vật liệu |
| DT-B05 | Không | Có | Có | Không | Trả về `null` |

## 6. Cách chạy

Từ thư mục gốc của project, chạy:

```powershell
./test/run-tests.cmd
```

Script biên dịch source và test bằng Java 21, sau đó chạy JUnit Platform Console. Thư viện JUnit nằm tại `test/lib`. File `.cmd` chỉ bỏ qua Execution Policy cho lần chạy test hiện tại, không thay đổi thiết lập Windows.
