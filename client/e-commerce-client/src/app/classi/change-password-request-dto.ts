import { LoginRequestDto } from './login-request-dto';

export class ChangePasswordRequestDto extends LoginRequestDto{
  doiCode: string;
  newPassword: string;
  oldPassword: string;
}
