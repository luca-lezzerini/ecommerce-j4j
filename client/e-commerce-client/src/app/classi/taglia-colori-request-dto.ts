import { Prodotto } from './prodotto';
import { Taglia } from './taglia';
import { LoginResponseDto } from './login-response-dto';
export class TagliaColoriRequestDto extends LoginResponseDto {
  taglia: Taglia;
  prodotto: Prodotto;
}
