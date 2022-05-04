import { CHANNEL_TAGS } from "./constant";

export function channelTagFormatter(row) {
    const tag = CHANNEL_TAGS.find(item => item.value === row.type);

    if (tag) {
        return tag.name;
    }
    return '';
}

export function vipFormatter(row) {
    if (row.vip === 1) {
        return '是';
    }
    return '否';
}

export function recommendFormatter(row) {
    if (row.recommend === 1) {
        return '是';
    }
    return '否';
}