import { CHANNEL_TAGS } from "./constant";

export function channelTagFormatter(row) {
    const tag = CHANNEL_TAGS.find(item => item.value === row.type);

    if (tag) {
        return tag.name;
    }
    return '';
}