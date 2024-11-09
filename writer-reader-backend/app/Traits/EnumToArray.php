<?php

namespace App\Traits;

/**
 * Trait EnumToArray
 * @package App\Traits
 * Provides a way to convert an enum to an array of names, values, or a key-value pair.
 */
trait EnumToArray
{
    public static function names(): array
    {
        return array_column(self::cases(), 'name');
    }
    public static function values(): array
    {
        return array_column(self::cases(), 'value');
    }

    public static function array(): array
    {
        return array_combine(self::values(), self::names());
    }
}
