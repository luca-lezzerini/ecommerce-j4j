import { LoginResponseDto } from './login-response-dto';
import { Taglia } from './taglia';

export class TagliaCreateDto extends LoginResponseDto {
    dati: Taglia;
}