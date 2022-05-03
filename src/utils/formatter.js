export function vipFormatter(row) {
    if (row.vip === 1) {
        return '是';
    }
    return '否';
}