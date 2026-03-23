package com.hungnv.backend.modules.yard.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SlotAllocationService {
    private final JdbcTemplate jdbcTemplate;

    public SlotAllocationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> suggestSlot(String containerId, Integer blockId) {
        Integer size = jdbcTemplate.queryForObject(
                "select ct.size from container c join container_types ct on ct.container_type_id = c.container_type_id where c.container_id = ?",
                Integer.class,
                containerId
        );
        String base = """
                select s.slot_id,
                       s.max_tier,
                       count(cp.position_id) as occupied
                from slots s
                left join container_positions cp on cp.slot_id = s.slot_id
                where s.supported_size = ?
                """;
        Object[] params;
        if (blockId != null) {
            base += " and s.block_id = ? ";
            params = new Object[]{size, blockId};
        } else {
            params = new Object[]{size};
        }
        base += " group by s.slot_id, s.max_tier order by occupied asc, s.slot_id asc limit 1";
        Map<String, Object> row = jdbcTemplate.queryForMap(base, params);
        int maxTier = ((Number) row.get("max_tier")).intValue();
        int occupied = ((Number) row.get("occupied")).intValue();
        int tier = Math.min(maxTier, occupied + 1);
        return Map.of(
                "containerId", containerId,
                "supportedSize", size,
                "slotId", row.get("slot_id"),
                "tier", tier,
                "occupied", occupied,
                "maxTier", maxTier
        );
    }
}

