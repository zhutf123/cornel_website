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

export function bannerTypeFormatter(row) {
    if (row.type === 1) {
        return '剧集';
    }
    return '广告';
}

export function genderFormatter(row) {
    if (row.gender === 0) {
        return '男';
    }
    return '女';
}

export function depictLengthFormatter(row) {
    if (row.depict && row.depict.length > 20) {
        return row.depict.slice(0, 20) + '...';
    }
    return row.depict;
}